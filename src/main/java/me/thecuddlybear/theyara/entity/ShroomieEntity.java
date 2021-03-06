package me.thecuddlybear.theyara.entity;

import com.google.common.collect.Maps;
import me.thecuddlybear.theyara.TheYara;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class ShroomieEntity extends TameableEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    public static final Predicate<LivingEntity> FOLLOW_TAMED_PREDICATE;
    public static final int RED_TYPE = 0;
    public static final int BLUE_TYPE = 1;
    public static final int GREEN_TYPE = 2;
    public static final int PURPLE_TYPE = 3;
    public static final int PINK_TYPE = 4;
    public static final Map<Integer, Identifier> TEXTURES;
    private static final TrackedData<Integer> SHROOMIE_TYPE;

    public ShroomieEntity(EntityType<? extends TameableEntity> entityType, World world){
        super(entityType, world);
        this.setTamed(false);
        this.ignoreCameraFrustum = true;
    }

    public int getShroomieType() {
        return (Integer)this.dataTracker.get(SHROOMIE_TYPE);
    }

    public void setShroomieType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(5);
        }

        this.dataTracker.set(SHROOMIE_TYPE, type);
    }

    public Identifier getTexture() {
        return (Identifier)TEXTURES.getOrDefault(this.getShroomieType(), (Identifier)TEXTURES.get(0));
    }

    static {
        FOLLOW_TAMED_PREDICATE = (entity) -> {
            EntityType<?> entityType = entity.getType();
            return entityType == EntityType.SHEEP || entityType == EntityType.RABBIT || entityType == EntityType.FOX;
        };
        SHROOMIE_TYPE = DataTracker.registerData(ShroomieEntity.class, TrackedDataHandlerRegistry.INTEGER);
        TEXTURES = (Map)Util.make(Maps.newHashMap(), (map) -> {
            map.put(0, new Identifier("theyara", "textures/entity/shroomie/shroomie_0.png"));
            map.put(1, new Identifier("theyara", "textures/entity/shroomie/shroomie_1.png"));
            map.put(2, new Identifier("theyara", "textures/entity/shroomie/shroomie_2.png"));
            map.put(3, new Identifier("theyara", "textures/entity/shroomie/shroomie_3.png"));
            map.put(4, new Identifier("theyara", "textures/entity/shroomie/shroomie_4.png"));
        });
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.add(2, new SitGoal(this));
        this.targetSelector.add(5, new FollowTargetIfTamedGoal(this, AnimalEntity.class, false, FOLLOW_TAMED_PREDICATE));
        this.targetSelector.add(6, new FollowTargetIfTamedGoal(this, TurtleEntity.class, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }

    @Override
    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        if (world.getMoonSize() > 0.9F) {
            this.setShroomieType(this.random.nextInt(5));
        } else {
            this.setShroomieType(this.random.nextInt(5));
        }
        return entityData;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("ShroomieType", this.getShroomieType());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setShroomieType(nbt.getInt("ShroomieType"));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHROOMIE_TYPE, 0);
    }

    public static Builder createShroomieAttributes() {
        return TameableEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return super.canBeLeashedBy(player);
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0D, (double)(0.6F * this.getStandingEyeHeight()), (double)(this.getWidth() * 0.4F));
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        ShroomieEntity shroomieEntity = (ShroomieEntity)TheYara.SHROOMIE.create(world);
        UUID uUID = this.getOwnerUuid();
        if (uUID != null) {
            shroomieEntity.setOwnerUuid(uUID);
            shroomieEntity.setTamed(true);
        }

        if (entity instanceof CatEntity) {
            if (this.random.nextBoolean()) {
                shroomieEntity.setShroomieType(this.getShroomieType());
            } else {
                shroomieEntity.setShroomieType(((ShroomieEntity)entity).getShroomieType());
            }
        }

        return shroomieEntity;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if(this.isInSittingPose()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shroomie.sit", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shroomie.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    public boolean isBreedingItem(ItemStack stack) {
        Item item = stack.getItem();
        return item.isFood() && item.getFoodComponent().isMeat();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.world.isClient) {
            boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(Items.RED_MUSHROOM) || itemStack.isOf(Items.BROWN_MUSHROOM) && !this.isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (this.isTamed()) {
                if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    this.heal((float)item.getFoodComponent().getHunger());
                    this.emitGameEvent(GameEvent.MOB_INTERACT, this.getCameraBlockPos());
                    return ActionResult.SUCCESS;
                }else if(this.isSitting()){
                    this.setSitting(false);
                    this.setInSittingPose(false);
                }else{
                    this.setSitting(true);
                    this.setInSittingPose(true);
                }

                /*if (!(item instanceof DyeItem)) {
                    ActionResult actionResult = super.interactMob(player, hand);
                    if ((!actionResult.isAccepted() || this.isBaby()) && this.isOwner(player)) {
                        this.setSitting(!this.isSitting());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget((LivingEntity)null);
                        return ActionResult.SUCCESS;
                    }

                    return actionResult;
                }

                DyeColor dyeColor = ((DyeItem)item).getColor();
                if (dyeColor != this.getCollarColor()) {
                    this.setCollarColor(dyeColor);
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    return ActionResult.SUCCESS;
                }*/
            } else if (itemStack.isOf(Items.RED_MUSHROOM) || itemStack.isOf(Items.BROWN_MUSHROOM)) {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    this.setTamed(true);
                    this.setSitting(true);
                    this.world.sendEntityStatus(this, (byte)7);
                } else {
                    this.world.sendEntityStatus(this, (byte)6);
                }

                return ActionResult.SUCCESS;
            }

            return super.interactMob(player, hand);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getAttacker();
            this.setSitting(false);
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof PersistentProjectileEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.damage(source, amount);
        }
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
